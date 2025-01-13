import React from "react";
import Image from "next/image";
interface LogoProps {
  logoSrc: string;
  alt: string;
  width: number;
  height: number;
}

const HeaderLogo: React.FC<LogoProps> = ({ logoSrc, alt, width, height }) => {
  return (
    <div className="">
      <Image
        src={logoSrc}
        alt={alt}
        className="object-contain max-w-none"
        width={width}
        height={height}
      />
    </div>
  );
};

export default HeaderLogo;
