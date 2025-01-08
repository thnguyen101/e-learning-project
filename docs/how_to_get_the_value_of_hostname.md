

Here are ways to retrieve the hostname on Linux, Windows, and macOS systems:

---

### **Linux**
1. **Using the `hostname` command**:
   ```bash
   hostname
   ```
2. **Using `uname`**:
   ```bash
   uname -n
   ```
3. **Reading `/etc/hostname`**:
   ```bash
   cat /etc/hostname
   ```

---

### **Windows**
1. **Using the Command Prompt**:
   ```cmd
   hostname
   ```
2. **Using PowerShell**:
   ```powershell
   $env:COMPUTERNAME
   ```
   or
   ```powershell
   [System.Net.Dns]::GetHostName()
   ```

---

### **macOS**
1. **Using the `hostname` command**:
   ```bash
   hostname
   ```
2. **Using `scutil`**:
   ```bash
   scutil --get HostName
   ```
3. **Using `uname`**:
   ```bash
   uname -n
   ```