#!/bin/bash

if [[ $# -eq 0 ]] ; then
    echo '1 argument required: URI of resource...'
    exit 1
fi

curl -4 -v -X GET $1 -o image.jpg
