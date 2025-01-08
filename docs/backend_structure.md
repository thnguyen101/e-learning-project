Here is the structure of the backend application:

---

```sh
main
├── java
│   ├── com
│   │   └── el
│   │       ├── common
│   │       │   ├── auth
│   │       │   │   ├── application
│   │       │   │   │   └── impl
│   │       │   │   └── web
│   │       │   │       └── dto
│   │       │   ├── config
│   │       │   │   └── jackson
│   │       │   ├── exception
│   │       │   ├── projection
│   │       │   └── upload
│   │       │       ├── application
│   │       │       │   ├── dto
│   │       │       │   └── impl
│   │       │       │       └── awss3
│   │       │       └── web
│   │       │           └── dto
│   │       ├── utils
│   │       │   ├── keystore
│   │       │   ├── pdfgen
│   │       │   └── signer
│   │       ├── course
│   │       │   ├── application
│   │       │   │   ├── dto
│   │       │   │   |    └── teacher
│   │       │   |   └── impl
│   │       │   ├── domain
│   │       │   └── web
│   │       │       └── dto
│   │       │       └── validate
│   │       ├── coursepath
│   │       │   ├── application
│   │       │   │   └── dto
│   │       │   │   └── impl
│   │       │   ├── domain
│   │       │   └── web
│   │       │       └── dto
│   │       ├── discount
│   │       │   ├── application
│   │       │   │   └── dto
│   │       │   │   └── impl
│   │       │   ├── domain
│   │       │   └── web
│   │       │       └── dto
│   │       │       └── validate
│   │       ├── enrollment
│   │       │   ├── adapter
│   │       │   ├── application
│   │       │   │   ├── dto
│   │       │   │   └── impl
│   │       │   ├── domain
│   │       │   └── web
│   │       │       └── dto
│   │       │       └── validate
│   │       ├── order
│   │       │   ├── application
│   │       │   │   └── impl
│   │       │   ├── domain
│   │       │   └── web
│   │       │       └── dto
│   │       ├── payment
│   │       │   ├── application
│   │       │   ├── domain
│   │       │   └── web
│   │       │       └── dto
│   │       ├── salary
│   │       │   ├── application
│   │       │   │   └── impl
│   │       │   ├── domain
│   │       │   └── web
│   ├── resources
│   │   └── db
│   │       └── migration
└── test
    ├── java
    │   └── com
    │       └── el
    │           ├── course
    │           │   ├── application
    │           │   ├── domain
    │           │   └── web
    │           ├── discount
    │           │   ├── application
    │           │   ├── domain
    │           │   └── web
    │           ├── enrollment
    │           │   ├── application
    │           │   ├── domain
    │           │   └── web
    │           ├── order
    │           │   ├── application
    │           │   ├── domain
    │           │   └── web
    │           ├── payment
    │           │   ├── application
    │           │   ├── domain
    │           │   └── web
    └── resources

```

