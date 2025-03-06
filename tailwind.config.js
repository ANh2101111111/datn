/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js,jsx,ts,tsx}"],
  theme: {
    extend: {
      colors: {
        // Brand Color
        brand: {
          primary: "#81B13D",
          secondary: "#FDC040",
          thrid: "#FFFFFF",
        },
        // Scale Colors
        scale: {
          color1: "#5BD934",
          color2: "#3A882B",
          color3: "#227226",
          color4: "#1A5D2C",
          color5: "#3BB77E",
          color6: "#FD6E6E",
        },
        // System Colors
        system: {
          primary: "#46BCF2",
          success: "#16C79A",
          danger: "#ef4f4f",
          warning: "#F6C065",
          info: "#008891",
        },
        // Text Colors
        text: {
          heading: "#253D4E",
          body: "#7e7e7e",
          footer: "#253D4E",
          brand1: "#3BB77E",
          muted: "#B6B6B6",
        },
        colorButton: {
          brand1: "#DEF9EC",
          brand1hover: "#FDC040",
        },
        border: {
          color1: "#E5E5E5",
          grey: "#F2F3F4",
        },
        badge: {
          Danger: "#FD6E6E",
          "brand-1": "#3BB77E",
          "brand-2": "#FDC040",
        },
        icon: {
          star: "#CDCDCD",
        },
        colorButton: {
          brand1: "#DEF9EC",
          brand1hover: "#FDC040",
        },
        border: {
          color1: "#E5E5E5",
        },
        badge: {
          Danger: "#FD6E6E",
          "brand-1": "#3BB77E",
          "brand-2": "#FDC040",
        },
        icon: {
          star: "#CDCDCD",
        },
        badge: {
          Danger: "#FD6E6E",
          "brand-1": "#3BB77E",
          "brand-2": "#FDC040",
        },
        icon: {
          star: "#CDCDCD",
        },
      },

      fontFamily: {
        lato: ["var(--font-lato)", "sans-serif"],
        quicksand: ["var(--font-quicksand)", "sans-serif"],
      },
      fontSize: {
        "display-1": ["96px", { lineHeight: "128px", letterSpacing: "0px" }],
        "display-2": ["72px", { lineHeight: "96px", letterSpacing: "0px" }],
        "heading-1": ["48px", { lineHeight: "64px", letterSpacing: "0px" }],
        "heading-2": ["40px", { lineHeight: "48px", letterSpacing: "0px" }],
        "heading-3": ["32px", { lineHeight: "40px", letterSpacing: "0px" }],
        "heading-4": ["24px", { lineHeight: "32px", letterSpacing: "0px" }],
        "heading-5": ["20px", { lineHeight: "24px", letterSpacing: "0px" }],
        "heading-6": ["16px", { lineHeight: "20px", letterSpacing: "0px" }],
        "heading-sm": ["14px", { lineHeight: "16px", letterSpacing: "0px" }],
        "text-large": ["18px", { lineHeight: "26px", letterSpacing: "0px" }],
        "text-medium": ["16px", { lineHeight: "24px", letterSpacing: "0px" }],
        "text-small": ["14px", { lineHeight: "20px", letterSpacing: "0px" }],
        "text-xs": ["12px", { lineHeight: "18px", letterSpacing: "0px" }],
      },
      container: {
        center: true,
        screens: {
          xl: "1280px",
        },
      },
      gridTemplateColumns: {
        20: "repeat(20, minmax(0, 1fr))",
        15: "repeat(15, minmax(0, 1fr))",
      },
    },
  },
  plugins: [],
};
