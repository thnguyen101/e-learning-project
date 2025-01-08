Here is the structure of the frontend application:

---


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
