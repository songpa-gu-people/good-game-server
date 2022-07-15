#!/bin/bash

set -e

cd "$(dirname "$0")"

# Localstack
docker-compose up -d --remove-orphans
