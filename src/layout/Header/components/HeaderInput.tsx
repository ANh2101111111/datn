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
    <div className="flex items-center border-2 border-[#BCE3C9] rounded-sm w-[700px] h-[37px]">
      <div className="flex items-center px-2">
        <select className=" bg-transparent focus:outline-none text-gray-700">
          {categories.map((category, index) => (
            <option key={index} value={category}>
              {category}
            </option>
          ))}
        </select>
      </div>

      <input
        type="text"
        placeholder="Search for items"
        className="flex-grow  focus:outline-none text-gray-700"
      />

      {/* Search Button */}
      {searchIcon && (
        <button className="text-white px-4 flex items-center">
          {React.createElement(searchIcon, { className: "w-5 h-5" })}
        </button>
      )}
    </div>
  );
};

export default HeaderInput;
