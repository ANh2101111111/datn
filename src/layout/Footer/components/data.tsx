import { IconAddress } from "@/layout/assets/icons";
import IconCall from "@/layout/assets/icons/IconCall";
import IconEmail from "@/layout/assets/icons/IconEmail";
import IconTime from "@/layout/assets/icons/IconTime";

export const DATA = [
  {
    title: "Company",
    texts: [
      "About Us",
      "Delivery Information",
      "Privacy Policy",
      "Terms & Conditions",
      "Contact Us",
      "Support Center",
      "Careers",
    ],
  },
  {
    title: "Account",
    texts: [
      "Sign In",
      "View Cart",
      "My Wishlist",
      "Track My Order",
      "Help Ticket",
      "Shipping Details",
      "Compare Products",
    ],
  },
  {
    title: "Settings",
    texts: ["Setting Account", "View All Order", "Collection"],
  },
  {
    title: "Information",
    texts: [
      "Search Terms",
      "Advanced Search",
      "Help & FAQ's",
      "Store Location",
      "Orders & Returns",
      "Feedback for us",
    ],
  },
];

export const LOGO_DATA = [
  {
    icon: <IconAddress />,
    title: "Address: ",
    description: "5171 W Campbell Ave undefined Kent, Utah 53127 United States",
  },
  {
    icon: <IconCall />,
    title: "Call Us: ",
    description: " (+91) - 540-025-124553 ",
  },
  {
    icon: <IconEmail />,
    title: "Email: ",
    description: "Email: contact@nestmart.com",
  },
  {
    icon: <IconTime />,
    title: "Hours: ",
    description: "10:00 - 18:00, Mon - Sat ",
  },
];

// PAYMENT
export const APP_PAYMENT_DATA = {
  title: "App & Payment",
  description: "Install NetMart App from App Store or Google Play",
  apps: [
    {
      image: "/AppStoteImage.svg",
      alt: "App Store",
    },
    {
      image: "/GooglePlay.svg",
      alt: "Google Play",
    },
  ],
  paymentGateways: [
    {
      image: "/payment-method.svg",
      alt: "Visa",
    },
  ],
};
