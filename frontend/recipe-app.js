const urlParams = new URLSearchParams(window.location.search);
const recipeId = urlParams.get("id");


const btn = document.getElementById("btn");

btn.addEventListener("click", function handleClick(event) {
    event.preventDefault();
    const reviewText = document.getElementById("review-text");
    console.log(reviewText.value);
   
    

    fetch("http://localhost:8080/recipes/review/"+recipeId+"/"+reviewText.value, {
        method: "POST",
        });
   
location.reload(); // för att visa nya posetiva(bör va) reviewen!
reviewText.value = "";
alert("Review submitted");

});


fetch("http://localhost:8080/recipes/"+recipeId)
  .then(response => response.json())
  .then(recipe => {
    const recipeName = document.getElementById("recipe-name");
    recipeName.textContent = recipe.recipeName;

    const title = document.getElementById("title");
    title.textContent = recipe.recipeName;
    
    const descriptionString = document.getElementById("recipe-description");
    descriptionString.textContent = recipe.description;


    const ingredientList = document.getElementById("ingredient-list");
    recipe.ingredients.forEach(ingredient => {
      const listItem = document.createElement("li");
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
        listItem.textContent = review.comment;
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


