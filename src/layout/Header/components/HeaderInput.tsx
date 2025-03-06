// HeaderInput.tsx
import React, { FC, SVGProps } from "react";

interface SearchIcon {
  key: string;
  icon: FC<SVGProps<SVGSVGElement>>;
}

interface SearchProps {
  categories: string[];
  icons: SearchIcon[];
}

const HeaderInput: React.FC<SearchProps> = ({ categories, icons }) => {
  const searchIcon = icons.find((icon) => icon.key === "search")?.icon;

  return (
    <div className="flex items-center border-2 border-[#BCE3C9] rounded-md w-full max-w-xl mx-auto">
      {/* Category Dropdown */}
      <div className=" md:flex items-center px-2 border-r border-[#BCE3C9]">
        <select className="bg-transparent focus:outline-none text-gray-700 text-sm md:text-base py-2">
          {categories.map((category, index) => (
            <option key={index} value={category}>
              {category}
            </option>
          ))}
        </select>
      </div>

      {/* Search Input */}
      <input
        type="text"
        placeholder="Search for items"
        className="flex-grow focus:outline-none text-gray-700 px-2 py-2 text-sm md:text-base w-full"
      />

      {/* Search Button */}
      {searchIcon && (
        <button className="text-gray-600 hover:text-gray-800 px-3 md:px-4 py-2 flex items-center">
          {React.createElement(searchIcon, {
            className: "w-4 h-4 md:w-5 md:h-5",
          })}
        </button>
      )}
    </div>
  );
};

export default HeaderInput;
