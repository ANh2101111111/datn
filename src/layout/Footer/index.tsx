import React from "react";
import BoxFooterInfo from "./components/BoxFooter";
import BoxLogo from "./components/BoxLogo";
import { DATA } from "./components/data";

const Footer: React.FC = () => {
  return (
    <footer
      className="grid grid-cols-2 md:grid-cols-4 gap-20 p-8"
      style={{
        width: "100%", // Đảm bảo footer chiếm toàn bộ chiều rộng
        maxWidth: "1556.78px", // Giới hạn chiều rộng tối đa
        margin: "0 auto", // Căn giữa nội dung
      }}
    >
      <BoxLogo />
      {DATA.map((section, index) => (
        <BoxFooterInfo
          key={index}
          title={section.title}
          texts={section.texts}
        />
      ))}
    </footer>
  );
};

export default Footer;
