'use client'
import React from "react";
import { twMerge } from "tailwind-merge";

interface IInputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  variant?: "normal" | "muted" | "focus" | "error";
  label?: string;
  error?: string;
  required?: boolean;
}

const variants = {
  normal: "border border-gray-200 bg-white",
  muted: "border border-gray-100 bg-gray-50",
  focus: "border border-green-500 ring-1 ring-green-500",
  error: "border border-red-500 bg-red-50",
};

const Input = React.forwardRef<HTMLInputElement, IInputProps>(
  (
    { className, variant = "normal", label, error, required = false, ...props },
    ref
  ) => {
    const inputRef = React.useRef<HTMLInputElement>(null);

    React.useImperativeHandle(ref, () => inputRef.current as HTMLInputElement);

    return (
      <div className="flex flex-col gap-1">
        {label && (
          <label
            className="text-sm font-medium text-gray-700"
            onClick={() => inputRef.current?.focus()}
          >
            {label}
            {required && <span className="text-red-500 ml-1">*</span>}
          </label>
        )}

        <div className="relative">
          <input
            ref={inputRef}
            className={twMerge(
              "w-full px-4 py-2 rounded-lg outline-none transition-colors",
              "placeholder:text-gray-400 text-gray-900",
              "focus:ring-2 focus:ring-green-500 focus:border-transparent",
              variants[variant],
              className
            )}
            {...props}
          />
        </div>

        {error && <p className="text-sm text-red-500 mt-1">{error}</p>}
      </div>
    );
  }
);

Input.displayName = "Input";

export default Input;
