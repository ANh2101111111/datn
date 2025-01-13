import IconWishlist from "@/layout/assets/icons/IconAccount";
import IconAccount from "@/layout/assets/icons/IconAccount";
import IconCart from "@/layout/assets/icons/IconCart";
import IconCompare from "@/layout/assets/icons/IconCompare";

export const DATA = {
  logoSrc: "/logo.svg",
  altText: "Nest Logo",
  alt:"logo",
  logoWidth: 215, 
  logoHeight: 66, 
  categories: ["All Categories"],
  icons: [
    { icon: IconAccount, label: "Account" },
    { icon: IconCompare, label: "Compare" },
    { icon: IconWishlist, label: "Wishlist" },
    { icon: IconCart, label: "Cart", count: 4 },
  ],
};
