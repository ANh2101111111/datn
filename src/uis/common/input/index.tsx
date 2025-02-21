// import React, { InputHTMLAttributes, forwardRef } from 'react';

// interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
//   label?: string;
//   error?: string;
//   required?: boolean;
//   muted?: boolean;
// }

// const Input = forwardRef<HTMLInputElement, InputProps>(
//   ({ className, label, error, required, muted, disabled, ...props }, ref) => {
//     const baseStyles = "w-[540px] h-16 px-4 bg-white border rounded-[10px] focus:outline-none";
//     const states = {
//       normal: "border-[#E5E5E5] focus:border-blue-500",
//       muted: "bg-gray-50 border-[#E5E5E5] text-gray-500",
//       error: "border-red-500 focus:border-red-500",
//       focus: "border-blue-500",
//       disabled: "bg-gray-100 cursor-not-allowed",
//     };

//     const inputStyles = cn(
//       baseStyles,
//       states.normal,
//       muted && states.muted,
//       error && states.error,
//       disabled && states.disabled,
//       className
//     );

//     return (
//       <div className="relative">
//         {label && (
//           <label className="block text-sm font-medium text-gray-700 mb-1">
//             {label}
//             {required && <span className="text-red-500 ml-1">*</span>}
//           </label>
//         )}
//         <input
//           ref={ref}
//           disabled={disabled}
//           className={inputStyles}
//           {...props}
//         />
//         {error && (
//           <div className="text-red-500 text-sm mt-1">{error}</div>
//         )}
//       </div>
//     );
//   }
// );

// Input.displayName = 'Input';

