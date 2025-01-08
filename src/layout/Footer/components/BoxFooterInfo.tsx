import React, { FC } from "react";

interface Props {
  title: string;
  texts: string[];
}

const BoxFooterInfo: FC<Props> = ({ title, texts }) => {
  return (
    <div>
      <div className="text-heading-4 font-quicksand text-text-heading mb-[28.58px] font-semibold">
        {title}
      </div>
      {/* Hiển thị danh sách */}
      <div>
        {texts.map((item, index) => (
          <div key={index} className="text-text-medium font-lato font-normal text-text-heading">
            {item}
          </div>
        ))}
      </div>
    </div>
  );
};

export default BoxFooterInfo;
