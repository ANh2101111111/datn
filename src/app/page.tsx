import Chatbot from "@/uis/ai/Chatbot";
import SystemDesign from "@/uis/systemDesign/systemDesign";
import React from "react";

export default function Home() {
  return (
    <div className="mt-1">
      <Chatbot/>
      <SystemDesign />
    </div>
  );
}