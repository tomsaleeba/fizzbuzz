#!/bin/bash
set -euo pipefail
cd `dirname "$0"`

docker run \
  --rm \
  -it \
  -v $(pwd)/Fizzbuzz.java:/tmp/Fizzbuzz.java \
  --entrypoint sh \
  openjdk:12-alpine \
  -c 'cd /tmp && javac Fizzbuzz.java && java Fizzbuzz'
