#!/bin/bash
# download and install latest geckodriver for linux or mac.
# required for selenium to drive a firefox browser.

json=$(curl -s https://api.github.com/repos/mozilla/geckodriver/releases/latest)
if [[ $(uname) == "Darwin" ]]; then
    url=https://github.com/mozilla/geckodriver/releases/download/v0.22.0/geckodriver-v0.22.0-macos.tar.gz
elif [[ $(uname) == "Linux" ]]; then
    echo "Linux OS detected."
    url=https://github.com/mozilla/geckodriver/releases/download/v0.22.0/geckodriver-v0.22.0-linux64.tar.gz
else
    echo "Can't determine OS."
    exit 1
fi
curl -s -L "$url" | tar -xz

wget -qO- xvfb-chrome https://bitbucket.org/atlassian/docker-node-chrome-firefox/raw/ff180e2f16ea8639d4ca4a3abb0017ee23c2836c/scripts/xvfb-firefox
chmod +x geckodriver
echo $PWD
echo "Installed geckodriver binary in $PWD"