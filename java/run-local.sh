#!/bin/bash
set -euo pipefail
cd `dirname "$0"`

javac Fizzbuzz.java && java Fizzbuzz
