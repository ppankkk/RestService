#!/usr/bin/env bash
echo Install tools...

cd /opt
sudo wget -c http://download.virtualbox.org/virtualbox/5.2.18/VBoxGuestAdditions_5.2.18.iso
sudo mount VBoxGuestAdditions_5.2.18.iso -o loop /mnt
cd /mnt
sudo sh VBoxLinuxAdditions.run --nox11
cd /opt
sudo rm *.iso