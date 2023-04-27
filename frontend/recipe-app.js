const urlParams = new URLSearchParams(window.location.search);
const recipeId = urlParams.get("id");

let rating = 0;
// ifall ingen stjärna anges så skickas 0 till db och i db händer ingenting ifall värdet är 0.


 // https://www.codinglabweb.com/2022/12/star-rating-html-css-javascript.html 
 // Tiden tog slut så följde en tutorial för att skapa ett star system, men är med på hur allt funkar
  const stars = document.querySelectorAll(".stars i");
  stars.forEach((star, index1) => { 

    star.addEventListener("click", () => {
      rating = index1+1;

      stars.forEach((star, index2) => {
      
        index1 >= index2 ? star.classList.add("active") : star.classList.remove("active");
      });
    });
  });

  function calculate() {
    let portion = document.getElementById("portion").value;
  
    if (portion % 1 !== 0) { // runda ner om ojämnt
      portion = Math.floor(portion);
      document.getElementById("portion").value = portion; 
    }

    const ingredientList = document.getElementById("ingredient-list");

    ingredientList.innerHTML = "";

    recipe.ingredients.forEach(ingredient => {
      const listItem = document.createElement("li");

      let t = ingredient.quantity / recipe.portions * portion

      listItem.textContent = t + " " + ingredient.measure + " " + ingredient.name;


      if (ingredient.measure === null) {
        listItem.textContent = ingredient.quantity + " " + ingredient.name;
      } 

      if (ingredient.quantity === 0) {
        listItem.textContent = ingredient.name;  //sketchy lösning, förbättra?
      }

      ingredientList.appendChild(listItem);

    });
  
  }


   


const btn = document.getElementById("btn");

btn.addEventListener("click", function handleClick(event) {
    event.preventDefault();
    const reviewText = document.getElementById("review-text");

    console.log("rating at send"+rating)

    fetch("http://localhost:8080/recipes/review/"+recipeId+"/"+reviewText.value+"/"+rating, {
        method: "POST",
        });
   
location.reload(); // för att visa nya posetiva(bör va) reviewen! , bör hitta annat sätt att uppdatera listan dock
rating = 0;
reviewText.value = "";
alert("Review submitted");


});


let recipe;
fetch("http://localhost:8080/recipes/"+recipeId)
  .then(response => response.json())
  .then(fetchedRecipe => {

    recipe = fetchedRecipe;
    const recipeName = document.getElementById("recipe-name");
    recipeName.textContent = recipe.recipeName;

    const title = document.getElementById("title");
    title.textContent = recipe.recipeName;
    
    const descriptionString = document.getElementById("recipe-description");
    descriptionString.textContent = recipe.description;

    const image = document.getElementById("id-img");
    image.src = "http://localhost:8080/recipes/image/"+recipe.id;


    const ingredientList = document.getElementById("ingredient-list");
    recipe.ingredients.forEach(ingredient => {
      const listItem = document.createElement("li");

      document.getElementById("portion").value = recipe.portions;

      listItem.textContent = ingredient.quantity + " " + ingredient.measure + " " + ingredient.name;


      if (ingredient.measure === null) {
        listItem.textContent = ingredient.quantity + " " + ingredient.name;
      } 

      if (ingredient.quantity === 0) {
        listItem.textContent = ingredient.name;  //sketchy lösning, förbättra?
      }

      ingredientList.appendChild(listItem);

    });

    const reviewList = document.getElementById("review-list");
    recipe.reviews.forEach(review => {
        const listItem = document.createElement("li");
        // const comment = document.createElement("p");
        // const rating = document.createElement("p");
        const result = document.createElement("p");
        
        console.log("rating is"+review.rating)

        const comment = review.comment;
        let t = "";
        
    
        if (review.rating !==0){
          t = " "+review.rating+"★";
        } 

        result.textContent = comment + t;

 

        listItem.appendChild(result);

        
        


        reviewList.appendChild(listItem);

    });
    
    const instructionList = document.getElementById("instruction-list");
    recipe.instructions.forEach(instruction => {
        const  listItem = document.createElement("li");
        listItem.textContent =  instruction.instruction;

        instructionList.appendChild(listItem);
})


.catch(error => console.error(error));

});




