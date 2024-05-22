#!/usr/bin/env bash
@echo off
docker-compose -f springdata-mysql.yml down
docker-compose -f springdata-mysql.yml up -d
echo 'Build Complete'