#
# VARIABLES
VM_IP = "192.168.20.10"
GIT_USER = "John Doe"
DIR_HOME = "/home/vagrant"
HOST = "linux"

Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.hostname = "vagrant.ubuntu"
  config.vm.network "forwarded_port", guest: 3306, host: 3603
  config.vm.network "forwarded_port", guest: 8080, host: 8888
  config.vm.network "forwarded_port", guest: 80, host: 8880
  config.vm.network "private_network", ip: VM_IP
  config.vm.provider "virtualbox" do |vb|
    vb.name = "env_java"
    vb.linked_clone = true
    vb.memory = 1024
    vb.cpus = 2
  end

  # These tools are not optional. They are used by the others installations scripts.
  config.vm.provision "shell", path: "vagrant_sh/tools.sh"

  # Install Java
  config.vm.provision "shell", path: "vagrant_sh/java.sh"

  # SSH
  config.vm.provision "shell", path: "vagrant_sh/ssh.sh"

  # Install Maven
  config.vm.provision "shell", path: "vagrant_sh/maven.sh", :args => [DIR_HOME]

  # Install MySql
  config.vm.provision "shell", path: "vagrant_sh/my-sql.sh"

  # Install Tomcat
  config.vm.provision "shell", path: "vagrant_sh/tomcat.sh", :args => [DIR_HOME]

  # Install Git
  config.vm.provision "shell", path: "vagrant_sh/git.sh", :args => [GIT_USER, DIR_HOME]

  # Install guest additions
  config.vm.provision "shell", path: "vagrant_sh/vboxadd.sh"
end