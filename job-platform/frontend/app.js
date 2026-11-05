const API_URL = "http://localhost:8080/api/jobs";
const jobsElement = document.querySelector("#jobs");
const statusElement = document.querySelector("#status");
const countElement = document.querySelector("#count");

function escapeHtml(value) {
  return String(value ?? "").replace(/[&<>"']/g, char => ({
    "&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"
  })[char]);
}

function renderJobs(jobs) {
  countElement.textContent = `${jobs.length} opportunities`;
  if (!jobs.length) {
    jobsElement.innerHTML = '<div class="empty">No matching roles yet. Try a different search.</div>';
    return;
  }
  jobsElement.innerHTML = jobs.map(job => `
    <article class="job-card">
      <h3>${escapeHtml(job.title)}</h3>
      <div class="company">${escapeHtml(job.company)}</div>
      <div class="chips"><span class="chip">${escapeHtml(job.location)}</span><span class="chip">${escapeHtml(job.employmentType)}</span></div>
      <p class="description">${escapeHtml(job.description)}</p>
    </article>`).join("");
}

async function loadJobs() {
  statusElement.textContent = "Loading opportunities…";
  const params = new URLSearchParams();
  const q = document.querySelector("#query").value.trim();
  const location = document.querySelector("#location").value.trim();
  const type = document.querySelector("#type").value;
  if (q) params.set("q", q);
  if (location) params.set("location", location);
  if (type) params.set("type", type);
  try {
    const response = await fetch(`${API_URL}?${params}`);
    if (!response.ok) throw new Error(`API returned ${response.status}`);
    const jobs = await response.json();
    renderJobs(jobs);
    statusElement.textContent = "";
  } catch (error) {
    countElement.textContent = "API unavailable";
    jobsElement.innerHTML = '<div class="empty">Start the Spring Boot backend, then refresh. See the README for setup.</div>';
    statusElement.textContent = error.message;
  }
}
document.querySelector("#search").addEventListener("click", loadJobs);
document.querySelector("#query").addEventListener("keydown", event => { if (event.key === "Enter") loadJobs(); });
loadJobs();