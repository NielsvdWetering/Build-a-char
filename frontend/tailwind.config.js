/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {},
  },
  plugins: [require("daisyui")],
  daisyui: {
    themes: [
      {
        buildachar: {
          primary: "#989E80",
          "primary-content": "#403D3A",
          secondary: "#989E80",
          "secondary-content": "#403D3A",
          accent: "#DCE4C8",
          "accent-content": "#605B56",
          neutral: "",
          "base-100": "#605B56",
        },
      },
    ],
  },
};
