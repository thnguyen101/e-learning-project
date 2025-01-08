Here’s how to install Java using your preferred package managers on each OS:

---

### **Linux (Ubuntu) using SDKMAN**

1. **Install SDKMAN**:
   First, install SDKMAN if it’s not already installed:
   ```bash
   curl -s "https://get.sdkman.io" | bash
   ```
   Then, source the SDKMAN script:
   ```bash
   source "$HOME/.sdkman/bin/sdkman-init.sh"
   ```

2. **Install Java**:
   List the available Java versions:
   ```bash
   sdk list java
   ```
   Install the desired version (e.g., OpenJDK 17):
   ```bash
   sdk install java 17-open
   ```

3. **Verify**:
   After installation, verify the Java version:
   ```bash
   java -version
   ```

---

### **Windows using Chocolatey**

1. **Install Chocolatey** (if not already installed):
   Open PowerShell as Administrator and run:
   ```powershell
   Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.SecurityProtocolType]::Tls12; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
   ```

2. **Install Java**:
   Install the desired version of Java (e.g., OpenJDK 17):
   ```powershell
   choco install openjdk17
   ```

3. **Verify**:
   Verify the Java installation:
   ```cmd
   java -version
   ```

---

### **macOS using Homebrew**

1. **Install Homebrew** (if not already installed):
   Run this command to install Homebrew:
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```

2. **Install Java**:
   Install OpenJDK (e.g., version 17):
   ```bash
   brew install openjdk@17
   ```

3. **Set the `JAVA_HOME` environment variable**:
   After installing, you may need to link OpenJDK to the system:
   ```bash
   sudo ln -sfn $(brew --prefix openjdk@17)/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
   ```

4. **Verify**:
   Check that Java is installed:
   ```bash
   java -version
   ```

---

These methods should match your preferences for each OS! Let me know if you need further assistance with any of the steps.