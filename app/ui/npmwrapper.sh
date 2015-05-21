#!/bin/bash

# Switch to local app dir outside nfs share
pushd /home/vagrant/app

# Link back to package.json in nfs share
#ln -sf /app/package.json

# Delegate to proper npm
/nodejs/bin/npm $1

# Sync node_modules back to nfs share
rsync --recursive --links --times node_modules /app

# Do a little dance
popd