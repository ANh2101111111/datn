import IconCart from "@/layout/assets/icons/IconCart";
import IconHumburger from "@/layout/assets/icons/IconHamburger";
import IconSearch from "@/layout/assets/icons/IconSearch";

export const DATA = {
  logoSrc: "/LogoBike.svg",
  alt: "logo",
  logoWidth: 215,
  logoHeight: 66,

  icons: [{ icon: IconCart, label: "Cart" }],
};

export const SEARCH_DATA = {
  categories: ["All Categories", "Xe dap 1", "Xe dap 2"],
  icons: [{ key: "search", icon: IconSearch }],
};
export const HEADER_MOBILE_DATA = [{ icon: IconHumburger }];
