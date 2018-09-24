#!/bin/bash
# download and install latest chromedriver for linux or mac.
# required for selenium to drive a Chrome browser.

version=$(wget -qO- https://chromedriver.storage.googleapis.com/LATEST_RELEASE)
if [[ $(uname) == "Darwin" ]]; then
    url=https://chromedriver.storage.googleapis.com/$version/chromedriver_mac64.zip
elif [[ $(uname) == "Linux" ]]; then
    echo "Linux OS detected."
    url=https://chromedriver.storage.googleapis.com/$version/chromedriver_linux64.zip
else
    echo "Can't determine OS."
    exit 1
fi

wget -N $url -P ~/
unzip ~/chromedriver_linux64.zip

chmod +x chromedriver
echo "Installed chromedriver binary in $PWD"