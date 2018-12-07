#!/usr/bin/env bash
echo Install tools...
#Tools
sudo apt-get install -y -qq unzip
sudo apt-get install -y -qq xclip
sudo apt-get install -y -qq mc
sudo apt-get install -y -qq htop
#After installing software-properties-common I was able to use add-apt-repository without any further issue
sudo apt-get install -y -qq software-properties-common