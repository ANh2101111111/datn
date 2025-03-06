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
    <div className="flex items-center">
      <Image
        src={logoSrc}
        alt={alt}
        className="object-contain"
        width={width}
        height={height}
        priority
      />
    </div>
  );
};

export default HeaderLogo;
