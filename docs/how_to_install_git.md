Here’s how to install **Git** on **Linux (Ubuntu)**, **Windows**, and **macOS**:

---

### **Linux (Ubuntu)**

1. **Update package index**:
   ```bash
   sudo apt update
   ```

2. **Install Git**:
   ```bash
   sudo apt install git -y
   ```

3. **Verify the installation**:
   After installation, check the Git version:
   ```bash
   git --version
   ```

---

### **Windows**

1. **Download Git for Windows**:
    - Visit the [Git for Windows download page](https://git-scm.com/download/win).
    - The installer will automatically start downloading.

2. **Run the Installer**:
    - Follow the installation wizard. You can use the default settings or customize as needed.
    - Make sure to select the option to add Git to your system path during the installation.

3. **Verify Git Installation**:
   Open **Command Prompt** or **PowerShell** and check the Git version:
   ```cmd
   git --version
   ```

---

### **macOS**

#### Using Homebrew:
1. **Install Homebrew** (if not already installed):
   If Homebrew is not installed, run this command:
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```

2. **Install Git**:
   ```bash
   brew install git
   ```

3. **Verify Git Installation**:
   After installation, confirm the version:
   ```bash
   git --version
   ```

#### Without Homebrew (Using Xcode Command Line Tools):
1. Install Xcode Command Line Tools (Git comes with it):
   ```bash
   xcode-select --install
   ```

2. **Verify Git Installation**:
   Check the Git version:
   ```bash
   git --version
   ```

---

Once installed, Git should be ready to use on your system. Let me know if you need further instructions or setup details!