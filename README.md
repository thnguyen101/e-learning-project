```bash
 _____ _                       _           _
| ____| |      _ __  _ __ ___ (_) ___  ___| |_
|  _| | |     | '_ \| '__/ _ \| |/ _ \/ __| __|
| |___| |___  | |_) | | | (_) | |  __/ (__| |_ _
|_____|_____| | .__/|_|  \___// |\___|\___|\__(_)
              |_|           |__/
```

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/thnguyen101/e-learning-project/.github%2Fworkflows%2Fcommit-stage.yaml)
![GitHub License](https://img.shields.io/github/license/thnguyen101/e-learning-project)
![GitHub language count](https://img.shields.io/github/languages/count/thnguyen101/e-learning-project)
![GitHub top language](https://img.shields.io/github/languages/top/thnguyen101/e-learning-project)

---
## Introduction
### 🗄️ Frontend Structure

```sh
src
├── app
│   ├── browse-course                          # Module: Books
│   │   ├── page                               # Contains pages of the module
│   │   │   └── book-list                      # Page: Book List
│   │   │       ├── book-list.component.ts     # Component logic
│   │   │       ├── book-list.component.html   # Component template
│   │   │       └── book-list.component.css    # Component styles
│   │   ├── service                            # Service to call API
│   │   │   └── book.service.ts
│   │   └── model                              # Models for data transfer
│   │       └── book.ts
│   ├── common                                 # Shared common functionality
│   │   ├── components                         # Shared UI components
│   │   └── auth                               # Authentication-related files
│   ├── app.component.ts                       # Main app component
│   ├── app.component.html                     # Main app template
│   ├── app.component.css                      # Main app styles
│   ├── app.routes.ts                          # Routing configurations
├── favicon.ico
├── main.ts                                    # Entry point for the app
├── index.html                                 # Main HTML file
├── styles.css                                 # Global styles
└── assets                                     # Static assets (images, fonts, etc.)
```

---

## Prerequisites
- Git ([How?](docs/how_to_install_git.md))
- Java 17 ([How?](docs/how_to_install_java.md))
- Docker ([How?](docs/how_to_install_docker.md))

## Run application
```bash
curl -sSL https://raw.githubusercontent.com/thnguyen101/e-learning-project/main/install.sh | bash
```
Check it out at:
> http://**{hostname}**:7080/angular-ui/ 

---

## Q&A
- [How to get the value of `hostname`?](docs/how_to_get_the_value_of_hostname.md)
