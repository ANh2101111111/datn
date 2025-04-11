import IconWishlist from "@/layout/assets/icons/IconAccount";
import IconAccount from "@/layout/assets/icons/IconAccount";
import IconCart from "@/layout/assets/icons/IconCart";
import IconCompare from "@/layout/assets/icons/IconCompare";
import IconHumburger from "@/layout/assets/icons/IconHamburger";
import IconSearch from "@/layout/assets/icons/IconSearch";

export const DATA = {
  logoSrc: "/LogoBike.svg",
  alt: "logo",
  logoWidth: 215,
  logoHeight: 66,

  icons: [
    { icon: IconAccount, label: "Login", path: "/login" },
    {
      icon: IconAccount,
      label: "Account",
      path: "/dashboard",
      isPrivate: true,
    },
    { icon: IconCompare, label: "shop", path: "/shop" },
    { icon: IconWishlist, label: "Wishlist", path: "/wishlist" },
    { icon: IconCart, label: "Cart", path: "/cart", isPrivate: true },
  ],
};

export const SEARCH_DATA = {
  categories: ["All Categories", "Xe dap 1", "Xe dap 2"],
  icons: [{ key: "search", icon: IconSearch }],
};
export const HEADER_MOBILE_DATA = [{ icon: IconHumburger }];
