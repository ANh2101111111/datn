import React from "react";
import { twMerge } from "tailwind-merge";

interface IButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  loading?: boolean;
  variant?: "primary" | "secondary" | "outline" | "shadow" | "rounded";
  size?: "small" | "medium" | "large";
  prefixIcon?: React.ReactNode;
  suffixIcon?: React.ReactNode;
  fullWidth?: boolean;
}

const variants = {
  primary: "text-white",
  secondary: "bg-[#81B13D] text-white",
  outline:
    "border border-solid border-brand-primary bg-transparent text-brand-primary",
  shadow: "",
  rounded: "rounded-[30px]",
};

const sizes = {
  large: "px-[35px] py-5 text-text-medium ",
  medium: "px-5 py-3 text-text-medium",
  small: "px-3 py-1 text-text-small",
};

const Button = React.forwardRef<HTMLButtonElement, IButtonProps>(
  (
    {
      className,
      variant = "primary",
      size = "small",
      prefixIcon, 
      suffixIcon,
      fullWidth = false,
      children,
      ...props
    },
    ref
  ) => {
    return (
      <button
        ref={ref}
        className={twMerge(
          "font-medium font-lato bg-brand-primary shadow-shadow-btn rounded-lg hover:bg-red-500 flex gap-2 items-center justify-center",
          variants[variant],
          sizes[size],
          fullWidth ? 'w-full' : 'w-auto',
          className
        )}
        {...props}
      >
        {prefixIcon}
        {children}
        {suffixIcon}
      </button>
    );
  }
);

Button.displayName = "Button";

export default Button;