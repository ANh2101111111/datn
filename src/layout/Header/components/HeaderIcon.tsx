import React, { useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useUser } from "@/api/users/useUser";
import IconAccount from "@/layout/assets/icons/IconAccount";

interface Icon {
  icon: React.FC<React.SVGProps<SVGSVGElement>>;
  label: string;
}

interface IconsProps {
  icons: Icon[];
}

const HeaderIcons: React.FC<IconsProps> = ({ icons }) => {
  const router = useRouter();
  const username = useUser(); // Lấy username từ token
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  const handleLogout = () => {
    localStorage.removeItem("token"); // Xóa token khi logout
    window.dispatchEvent(new Event("storage")); // Kích hoạt sự kiện để cập nhật UI
    setIsDropdownOpen(false);
    router.push("/login"); // Chuyển hướng về trang login
  };

  return (
    <div className="flex items-center gap-4 md:gap-6 relative">
      {username ? (
        <div className="relative">
          <button
            onClick={() => setIsDropdownOpen(!isDropdownOpen)}
            className="flex items-center gap-2 text-sm font-lato text-gray-700 hover:text-green-500 transition"
          >
            <IconAccount /> {username}
          </button>

          {isDropdownOpen && (
            <div className="absolute right-0 mt-2 w-40 bg-white border rounded-md shadow-lg">
              <button
                onClick={handleLogout}
                className="block w-full text-left px-4 py-2 text-sm text-red-600 hover:bg-red-100"
              >
                Logout
              </button>
            </div>
          )}
        </div>
      ) : (
        <Link
          href="/login"
          className="text-sm font-lato text-gray-700 flex items-center gap-2 hover:text-green-500 transition"
        >
          <IconAccount /> Login
        </Link>
      )}

      {icons.map((item, index) => {
        const IconComponent = item.icon;
        return (
          <Link
            key={index}
            href={item.label === "Cart" ? "/cart" : "#"}
            className="flex items-center gap-2 group cursor-pointer"
          >
            <span className="text-base md:text-lg">
              <IconComponent />
            </span>
            <span className="text-sm font-lato text-gray-700 group-hover:text-green-500 transition-all duration-300">
              {item.label}
            </span>
          </Link>
        );
      })}
    </div>
  );
};

export default HeaderIcons;
