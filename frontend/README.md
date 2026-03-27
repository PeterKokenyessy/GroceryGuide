## Core Stack

- `react`: `^19.2.0`
- `react-dom`: `^19.2.0`
- `react-router-dom`: `^7.13.0`

---

## Design & UI

- `tailwindcss`: `^4.1.18`
- `tailwind-merge`: `^3.4.0`
- `class-variance-authority`: `^0.7.1`
- `clsx`: `^2.1.1`
- `tailwindcss-animate`: `^1.0.7`
- `@radix-ui/react-slot`: `^1.2.4`
- `lucide-react`: `^0.563.0`

---

## ⚠️ Important: shadcn/ui

This project uses **shadcn/ui**.  
Documentation: https://ui.shadcn.com/docs

**shadcn/ui is NOT installed as an npm package.**

shadcn/ui works by **generating component source files directly into the project**  
(typically under `src/components/ui`).

There is **no shadcn dependency in `node_modules`** — this is expected behavior.

---

## Getting Started

Install dependencies:


```bash
npm install
```
Run the development server:
```bash
npm run dev
```

## Troubleshooting

If you experience issues starting the development server:

1. Remove `node_modules` and reinstall dependencies:
    ``` bash
    rm -rf node_modules
    npm install
    ```
    
- `package-lock.json` is used to ensure consistent dependency versions.
Only delete it when troubleshooting dependency-related issues.

2. If the problem persists, perform a clean install:
    ```bash
    rm -rf node_modules package-lock.json
    npm install
    ```
