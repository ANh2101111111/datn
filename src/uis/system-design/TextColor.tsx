import React from "react";

interface TextColorProps {
  text: string;
  className: string;
}

const TextColor: React.FC<TextColorProps> = ({ text, className }) => {
  return <p className={className}>{text}</p>;
};

export default TextColor;
