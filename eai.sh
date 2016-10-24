#!/bin/bash

# Ask the user how many domain names they want
read N

COUNT=0
PAGE=0

while :
do

	# Grab the html from the current page of results,
	# Find the text where domain names are located,
	# Trim it down to just the domain name, 
	# Store the domain name in the RESULT variable.

	RESULT=$(curl -s "http://www.alexa.com/topsites/global;$PAGE" | grep -Eoh '(\/siteinfo\/)\S*"' | cut -c11- | sed 's/.$//')
	#RESULT=$(< topsites.html grep -Eoh '(\/siteinfo\/)\S*"' | cut -c11- | sed 's/.$//')
	
	
	# Print each domain name to STDOUT if the number printed so far hasn't passed N
    for word in $RESULT; do
    	if [[ $COUNT -lt $N ]]; then
    		echo $word
    	fi
    	# Increment count
    	let "COUNT=$COUNT+1"
    done

	# If the target number of domain names has been reached, stop looping
	if [[ $COUNT -gt $N-1 ]]; then
		break
	fi

	# Stop if you pass 500 domain names (only 500 are available)
	if [[ $COUNT -gt 499 ]]; then 
		break
	fi

	# If more domain names are needed, increment the page number and continue
	let "PAGE=$PAGE+1"

done

