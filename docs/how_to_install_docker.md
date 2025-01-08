

Here’s how to install Docker on **Linux (Ubuntu)**, **Windows**, and **macOS**:

---

### **Linux (Ubuntu)**

1. **Update the apt package index**:
   ```bash
   sudo apt update
   ```

2. **Install dependencies**:
   ```bash
   sudo apt install apt-transport-https ca-certificates curl software-properties-common -y
   ```

3. **Add Docker’s official GPG key**:
   ```bash
   curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
   ```

4. **Add Docker repository**:
   ```bash
   echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
   ```

5. **Install Docker**:
   ```bash
   sudo apt update
   sudo apt install docker-ce docker-ce-cli containerd.io -y
   ```

6. **Verify installation**:
   Check Docker version:
   ```bash
   docker --version
   ```

7. **(Optional) Run Docker without `sudo`**:
   ```bash
   sudo usermod -aG docker $USER
   ```
   Then, log out and log back in for the changes to take effect.

---

### **Windows**

1. **Download Docker Desktop**:
    - Visit the [Docker Desktop for Windows download page](https://www.docker.com/products/docker-desktop).
    - Download and run the installer.

2. **Run Docker Desktop**:
   Follow the prompts to complete the installation. Docker Desktop will automatically start after installation.

3. **Enable WSL 2 (if required)**:
   Docker Desktop for Windows requires WSL 2. If you haven't installed it yet:
    - Enable WSL and Virtual Machine Platform using PowerShell:
      ```powershell
      wsl --install
      ```
    - Restart your computer.

4. **Verify Docker installation**:
   Open Command Prompt or PowerShell and run:
   ```cmd
   docker --version
   ```

---

### **macOS**

1. **Download Docker Desktop for Mac**:
    - Visit the [Docker Desktop for Mac download page](https://www.docker.com/products/docker-desktop).
    - Download the `.dmg` file.

2. **Install Docker**:
    - Open the `.dmg` file and drag Docker into the Applications folder.

3. **Run Docker Desktop**:
   Launch Docker from the Applications folder. The Docker icon will appear in the menu bar.

4. **Verify installation**:
   Open Terminal and check Docker version:
   ```bash
   docker --version
   ```

---

### **Verify Docker is Running** (Across all OS)

To verify that Docker is installed correctly, you can run:
```bash
docker --version
```
Additionally, you can test by running the "Hello World" container:
```bash
docker run hello-world
```

This will confirm that Docker is properly installed and able to run containers. Let me know if you run into any issues!