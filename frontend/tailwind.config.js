/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,jsx,ts,tsx}"
  ],
  safelist: [
    "bg-yellow-600",
    "bg-blue-700",
    "bg-green-700",
    "bg-blue-800",
    "bg-red-700",
    "bg-red-800",
    "bg-blue-600",
    "bg-blue-700",
  ],
theme: {
  extend: { },
},
plugins: [],
}

