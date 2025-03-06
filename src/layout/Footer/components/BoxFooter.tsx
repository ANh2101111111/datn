import React, { FC } from "react";

interface Props {
  title: string;
  texts: string[];
}

const BoxFooter: FC<Props> = ({ title, texts }) => {
  return (
    <div className="md:col-span-3 col-span-1 gap-4">
      <div className="text-heading-4 font-quicksand text-text-footer font-semibold">
        {title}
      </div>
      {/* Hiển thị danh sách */}
      <div className="space-y-[10px] ">
        {texts.map((item, index) => (
          <div
            key={index}
            className="text-text-medium font-lato font-normal text-text-footer"
          >
            {item}
          </div>
        ))}
      </div>
    </div>
  );
};

export default BoxFooter;
