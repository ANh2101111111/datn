import type { Config } from 'tailwindcss';

const config: Config = {
  content: [
    "./app/**/*.{js,ts,jsx,tsx}",
    "./src/**/*.{js,ts,jsx,tsx}", 
    "./components/**/*.{js,ts,jsx,tsx}", 
  ],
  
  
  theme: {
    extend: {
      colors: {
        // Brand Colors
        brand: {
          primary: '#81B13D', 
          secondary: '#FDC040',
        },
        // Scale Colors
        scale: {
          color1: '#5BD934',
          color2: '#3A882B',
          color3: '#227226',
          color4: '#1A5D2C',
        },
        // System Colors
        system: {
          primary: '#46BCF2',
          success: '#16C79A',
          danger: '#ef4f4f',
          warning: '#F6C065',
          info: '#008891',
        },
        // Text Colors
        text: {
          heading: '#1b1b1b',
          body: '#7e7e7e',
        },
      },
    },
  },
  plugins: [],
};

export default config;
