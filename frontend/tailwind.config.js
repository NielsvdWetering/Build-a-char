/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        "custom-light": "0 5px 15px rgba(255, 255, 255, 0.2)",
        "custom-dark": "0 5px 15px rgba(0, 0, 0, 0.5)",
      },
    },
  },
  plugins: [require("daisyui")],
  daisyui: {
    themes: [
      {
        buildachar: {
          primary: "#989E80",
          "primary-content": "#403D3A",
          secondary: "DCE4C8",
          "secondary-content": "#605B56",
          accent: "#C13E3C",
          neutral: "#918a81",
          "base-100": "#fffff",
        },
      },
    ],
  },
};
