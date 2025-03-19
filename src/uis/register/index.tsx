/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import Button from "../common/button";
import Input from "../common/input";
import { useMutation } from "@tanstack/react-query";
import { register } from "@/api/users";
import toast from "react-hot-toast";

const Register = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

  const { mutateAsync } = useMutation(register, {
    onSuccess: (data) => {
      toast.success("Registration Successful!");
      router.push("/login");
    },
    onError: (error: any) => {
      setError(error.response?.data?.message || "Registration failed!");
      toast.error("Registration failed!");
    },
  });

  const handleRegister = async () => {
    setError("");

    if (!username || !email || !password || !confirmPassword) {
      setError("Please fill in all fields!");
      return;
    }
    if (password !== confirmPassword) {
      setError("Passwords do not match!");
      return;
    }
    if (password.length < 6) {
      setError("Password must be at least 6 characters long!");
      return;
    }

    await mutateAsync({ username, email, password, confirmPassword });
  };

  return (
    <div className="grid grid-cols-1 lg:grid-cols-1 max-w-[400px] w-full mx-auto gap-12 my-16">
      <div className="col-span-1">
        <p className="text-heading-2 text-text-heading font-semibold mb-8 font-quicksand">
          Create an Account
        </p>
        <div className="flex flex-col gap-6">
          <Input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
          <Input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
          <Input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
          <Input type="password" placeholder="Confirm Password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
          {error && <p className="text-red-500">{error}</p>}
          <Button className="h-16 text-text-medium text-brand-thrid" onClick={handleRegister}>
            Submit & Register
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Register;
