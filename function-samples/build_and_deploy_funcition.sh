#!/usr/bin/env bash
cd ./calculator
faas-cli build -f calculator.yml
faas-cli deploy -f calculator.yml