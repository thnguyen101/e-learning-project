```bash
 _____ _                       _           _
| ____| |      _ __  _ __ ___ (_) ___  ___| |_
|  _| | |     | '_ \| '__/ _ \| |/ _ \/ __| __|
| |___| |___  | |_) | | | (_) | |  __/ (__| |_ _
|_____|_____| | .__/|_|  \___// |\___|\___|\__(_)
              |_|           |__/
```

![GitHub License](https://img.shields.io/github/license/thnguyen101/e-learning-project)
![GitHub language count](https://img.shields.io/github/languages/count/thnguyen101/e-learning-project)
![GitHub top language](https://img.shields.io/github/languages/top/thnguyen101/e-learning-project)

## Table of Contents
<!-- TOC -->
  * [Introduction](#introduction)
  * [Prerequisites](#prerequisites)
  * [Run application](#run-application)
  * [Q&A](#qa)
<!-- TOC -->
---
## Introduction
- [🗄️ Frontend Structure](docs/frontend_structure.md)
- [🗄️ Backend Structure](docs/backend_structure.md)
---

## Prerequisites
- Git ([How?](docs/how_to_install_git.md))
- Java 17 ([How?](docs/how_to_install_java.md))
- Docker ([How?](docs/how_to_install_docker.md))

> **Hint:** You need to have the credential of the s3 storage and the stripe then put it in the config file at backend/lms/src/main/resources/application.yml to work.

## Run application
```bash
# For Linux and macOS
curl -sSL https://raw.githubusercontent.com/thnguyen101/e-learning-project/main/install.sh | bash
```

```bash
# For Windows - Open Git Bash
git clone https://github.com/thnguyen101/e-learning-project.git
cd e-learning-project
chmod +x build.sh
./build.sh without-angular
```

Check it out at:
> http://**{hostname}**:7080/angular-ui/ 

---

## Q&A
- [How to get the value of `hostname`?](docs/how_to_get_the_value_of_hostname.md)
