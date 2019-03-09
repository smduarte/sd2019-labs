#!/bin/bash

curl -4 -v -X POST http://localhost:9999/rest/media -H "Content-Type: application/octet-stream" --data-binary "@earth.jpg" 