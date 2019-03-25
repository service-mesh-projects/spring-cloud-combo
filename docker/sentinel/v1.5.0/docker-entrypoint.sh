#!/usr/bin/env bash
set -ex

exec java ${JAVA_OPTS} -jar *.jar --spring.config.location=./config/application.properties