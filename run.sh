#!/bin/bash

git stash
git pull --rebase
sbt test