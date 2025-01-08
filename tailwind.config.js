/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,js,jsx,ts,tsx}", 
  ],
  theme: {
    extend: {
      colors: {
        // Brand Color
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
      fontFamily: {
        lato: ['var(--font-lato)', 'sans-serif'],
        quicksand: ['var(--font-quicksand)', 'sans-serif'],
      },
      fontSize: {
        'display-1': ['96px', { lineHeight: '128px', letterSpacing: '0px' }],
        'display-2': ['72px', { lineHeight: '96px', letterSpacing: '0px' }],
        'heading-1': ['48px', { lineHeight: '64px', letterSpacing: '0px' }],
        'heading-2': ['40px', { lineHeight: '48px', letterSpacing: '0px' }],
        'heading-3': ['32px', { lineHeight: '40px', letterSpacing: '0px' }],
        'heading-4': ['24px', { lineHeight: '32px', letterSpacing: '0px' }],
        'heading-5': ['20px', { lineHeight: '24px', letterSpacing: '0px' }],
        'heading-6': ['16px', { lineHeight: '20px', letterSpacing: '0px' }],
        'heading-sm': ['14px', { lineHeight: '16px', letterSpacing: '0px' }],
        'text-large': ['18px', { lineHeight: '26px', letterSpacing: '0px' }],
        'text-medium': ['16px', { lineHeight: '24px', letterSpacing: '0px' }],
        'text-small': ['14px', { lineHeight: '21px', letterSpacing: '0px' }],
        'text-xs': ['12px', { lineHeight: '18px', letterSpacing: '0px' }],
      },
      
    },
  },
  plugins: [],
};
