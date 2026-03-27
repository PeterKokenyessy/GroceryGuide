import React from "react";
import { Github } from "lucide-react";
import { Link } from "react-router-dom";

const team = [
  {
    name: "Peti",
    role: "The Idea Machine",
    desc: "Always coding, always pushing. Lives in chaos but somehow makes things work. Can code for hours… also can fall asleep anywhere.",
    img: "members/peti.jpg",
    github: "https://github.com/PeterKokenyessy",
  },
  {
    name: "Zoli",
    role: "The Energy",
    desc: "“Fuck YEEE, push it till nosebleed.” Brings raw energy into everything — no half measures.",
    img: "members/zoli.jpg",
    github: "https://github.com/zoleman",
  },
  {
    name: "Áron",
    role: "The Ghost",
    desc: "You don’t see him… but things get done. Silent, efficient, disappears — then drops results.",
    img: "members/aron.jpg",
    github: "https://github.com/aron166",
  },
  {
    name: "Bóbita",
    role: "The Architect",
    desc: "Calm, precise, always thinking ahead. Builds everything on solid foundations — nothing rushed.",
    img: "members/bobita.png",
    github: "https://github.com/pbobita",
  },
];

export default function About() {
  return (
    <div className="bg-background text-foreground px-6 md:px-16 py-16">

      {/* HERO */}
      <section className="max-w-5xl mx-auto text-center mb-20">
        <h1 className="text-4xl md:text-5xl font-bold mb-6">
          About GroceryGuide
        </h1>
        <p className="text-lg text-foreground/70">
          We are a small startup building a smarter way to compare grocery prices
          and help you decide where to shop.
        </p>
      </section>

      {/* STORY */}
      <section className="max-w-4xl mx-auto mb-20 text-center">
        <h2 className="text-2xl font-semibold mb-4">Our Story</h2>
        <p className="text-foreground/70">
          GroceryGuide started from a simple idea by Peti — honestly, almost too simple.
          But the more we thought about it, the clearer it became:
          everyone needs this.
        </p>
      </section>

      {/* WHAT WE DO */}
      <section className="max-w-5xl mx-auto mb-20">
        <h2 className="text-2xl  font-semibold text-center mb-8">
          What We Do
        </h2>

        <div className="grid md:grid-cols-2  gap-6 text-foreground/80">
          <div className="p-6 rounded-2xl border-primary border ">
            Compare grocery prices across stores
          </div>
          <div className="p-6 rounded-2xl border border-primary">
            Suggest the best places to shop
          </div>
          <div className="p-6 rounded-2xl border border-primary">
            Save time and money
          </div>
          <div className="p-6 rounded-2xl border border-primary">
            Make smarter shopping decisions
          </div>
        </div>
      </section>

      {/* TECH STACK */}
      <section className="max-w-5xl mx-auto mb-20 text-center">
        <h2 className="text-2xl font-semibold mb-6">Tech Stack</h2>

        <div className="flex flex-wrap justify-center gap-3 text-sm">
          {[
            "Java",
            "Spring Boot",
            "React",
            "Vite",
            "PostgreSQL",
            "Docker",
            "Docker Compose",
            "Selenium",
            "GitHub Actions",
          ].map((tech) => (
            <span
              key={tech}
              className="px-4 py-2 rounded-full border hover:bg-primary  text-foreground/70"
            >
              {tech}
            </span>
          ))}
        </div>
      </section>

      {/* TEAM */}
      <section className="max-w-6xl mx-auto mb-20">
        <h2 className="text-2xl font-semibold text-center mb-10">
          Meet the Team
        </h2>

        <div className="grid sm:grid-cols-2 md:grid-cols-4 gap-8">
          {team.map((member) => (
            <div
              key={member.name}
              className="group rounded-2xl overflow-hidden border border-border hover:shadow-xl transition"
            >
              <div className="overflow-hidden">
                <img
                  src={member.img}
                  alt={member.name}
                  className="w-full h-64 object-cover group-hover:scale-105 transition duration-300"
                />
              </div>

              <div className="p-4">
                <h3 className="font-semibold text-lg">{member.name}</h3>
                <p className="text-sm text-foreground/60 mb-2">
                  {member.role}
                </p>
                <p className="text-sm text-foreground/70">
                  {member.desc}
                </p>
                <a
                  href={member.github}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="mt-4 inline-flex items-center gap-2 text-sm text-foreground/70 hover:text-foreground transition"
                >
                  <Github size={16} />
                  GitHub
                </a>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* CTA */}
      <section className="text-center">
        <h2 className="text-2xl font-semibold mb-4">
          Start shopping smarter
        </h2>
        <p className="text-foreground/70 mb-6">
          Let GroceryGuide show you where to save.
        </p>

        <button className="px-6 py-3 rounded-xl bg-foreground text-background font-medium hover:opacity-90 transition"
        >
          <Link to={"/"}>
            Get Started
          </Link>
        </button>
        <img src="/images/image.png" alt="the_pain" className="bm-3" />
      </section>
    </div>
  );
}