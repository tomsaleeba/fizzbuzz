#!/bin/bash
# Runs in a Docker container
cat fizzbuzz.js | docker run --rm -i node:lts-alpine
